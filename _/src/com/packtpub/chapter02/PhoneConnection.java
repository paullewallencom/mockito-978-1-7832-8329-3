package com.packtpub.chapter02;

public interface PhoneConnection {
  boolean activate(String connectionForUserName, String number);
  String generateBillFor(String number);
}
