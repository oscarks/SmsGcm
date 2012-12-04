package org.grails.plugins.smsgcmserver

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

class GcmServerService {
	private Sender senderInstance;
	private static final int MULTICAST_SIZE = 1000;
	private static final Executor threadPool = Executors.newFixedThreadPool(5);

	def devices=[:]
	def grailsApplication
	
	Sender getSender() {
		if (!senderInstance) {
			def key=grailsApplication.config.google.api.key
			senderInstance=new Sender(key);
		}
		senderInstance
	}
	
    def register(String regId, data=[:]) {
		synchronized (devices) {
			if (!(regId in devices)) {
				log.debug "Device resgistered: ${regId}"
			} else {
				log.debug "Device already resgistered (${regId}), data updated"
			}
			devices[regId]=data
		}
    }
	
	def unregister(String regId) {
		if (regId in devices) {
			synchronized (devices) {
				devices.remove(regId)
			}
			log.debug "Device ${regId} unregistered"
		} else log.debug "Device ${regId} not found"
	}
	public updateRegistration(String oldId, String newId) {
		log.info("Updating " + oldId + " to " + newId);
		synchronized (devices) {
			def data=devices[oldId]
			devices.remove(oldId);
		  	devices[newId]=data;
		}
	  }
	def sendMessage(String regId, Map msg) {
		Message.Builder mbuilder = new Message.Builder().collapseKey("1").timeToLive(3).delayWhileIdle(true)
		msg.each { k,v->
			mbuilder.addData(k, v)
		}
		Message message=mbuilder.build();
		sender.send(message, regId, 5);
	}
	def sendMessage(Map msg) {
		String status;
		def devs=this.devices.keySet()
		if (devs.isEmpty()) {
		  status = "Message ignored as there is no device registered!";
		} else {
		  // NOTE: check below is for demonstration purposes; a real application
		  // could always send a multicast, even for just one recipient
		  if (devs.size() == 1) {
			// send a single message using plain post
			String registrationId = devs[0];
			Result result=sendMessage(registrationId,msg)
			status = "Sent message to one device: " + result;
		  } else {
			// send a multicast message using JSON
			// must split in chunks of 1000 devices (GCM limit)
			int total = devs.size();
			List<String> partialDevices = new ArrayList<String>(total);
			int counter = 0;
			int tasks = 0;
			for (String device : devs) {
			  counter++;
			  partialDevices.add(device);
			  int partialSize = partialDevices.size();
			  if (partialSize == MULTICAST_SIZE || counter == total) {
				asyncSend(partialDevices,msg);
				partialDevices.clear();
				tasks++;
			  }
			}
			status = "Asynchronously sending " + tasks + " multicast messages to " +
				total + " devices";
		  }
		}
		status
	}
	
	private void asyncSend(List<String> partialDevices,Map msg) {
		// make a copy
		final List<String> devs = new ArrayList<String>(partialDevices);
		threadPool.execute(new Runnable() {
	
		  public void run() {
			Message.Builder mbuilder= new Message.Builder().collapseKey("1").timeToLive(3).delayWhileIdle(true)
			msg.each { k,v->
				mbuilder.addData(k, v)
			}
			Message message=mbuilder.build();
			MulticastResult multicastResult;
			try {
			  multicastResult = sender.send(message, devs, 5);
			} catch (IOException e) {
			  log.error("Error posting messages ${e.message}");
			  return;
			}
			List<Result> results = multicastResult.getResults();
			// analyze the results
			for (int i = 0; i < devs.size(); i++) {
			  String regId = devs.get(i);
			  Result result = results.get(i);
			  String messageId = result.getMessageId();
			  if (messageId != null) {
				log.debug("Succesfully sent message to device: " + regId +
					"; messageId = " + messageId);
				String canonicalRegId = result.getCanonicalRegistrationId();
				if (canonicalRegId != null) {
				  // same device has more than on registration id: update it
				  log.info("canonicalRegId " + canonicalRegId);
				  updateRegistration(regId, canonicalRegId);
				}
			  } else {
				String error = result.getErrorCodeName();
				if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
				  // application has been removed from device - unregister it
				  log.info("Unregistered device: " + regId);
				  unregister(regId);
				} else {
				  log.error("Error sending message to " + regId + ": " + error);
				}
			  }
			}
		  }});
	  }
	
}
