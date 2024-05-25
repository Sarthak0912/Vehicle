package com.vehicle.vehicle_information.exception;

import org.apache.logging.log4j.message.Message;

public class DataNotFoundException extends RuntimeException{

  public DataNotFoundException(String message){
      super(message);
  }



}
