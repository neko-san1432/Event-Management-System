public class DataPreparation {
   SendCommand sendCommand;
  public Object[][] prepareEventDataForAdmin(){
    String command = "select eventid, eventName,dates, venue_name,concat(users.fname,' ',users.lName), number_of_guests,startofevent\n" +
    "from customers,listevents,users,venue \n" +
    "where venueid =venue_id"
    /*"order by eventID;"*/;
    sendCommand = new SendCommand(command,1,7);
    return sendCommand.getData();
  }

  public Object[][] prepareEventDataForClient() {
    String command = "select eventid, eventName,dates, venue_name,concat(users.fname,' ',users.lName), number_of_guests,startofevent\n" +
    "from customers,listevents,users,venue \n" +
    "where venueid =venue_id\n" +
    "order by listevents.eventID;";
    sendCommand = new SendCommand(command,1,6);
    return sendCommand.getData();
  }

  public Object[][] prepareTransactionDataForClient() {
    String command = "select trans_id,eventName,venue_name, concat(customers.fname,' ',customers.lName),cost*hoursInVenue, (cost*hoursInVenue)-downpayment,stat\n" +
    "from customers,transactions,users,listevents,venue\n" +
    "where listevents.faciid = users.faci_id\n" +
    "order by listevents.eventID\n";
    sendCommand = new SendCommand(command,1,7);
    return sendCommand.getData();
  }

  public Object[][] prepareTransactionDataForAdmin() {
    String command = "select distinct trans_id,eventid,concat(users.fname,' ',users.lName), concat(customers.fname,' ',customers.lName),total_due, total_due-downpayment,stat\n" +
    "from customers,transactions,users,listevents,venue\n" +
    "where transactions.trans_id=listevents.eventid"
    /*"order by listevents.eventID;"*/;
    sendCommand = new SendCommand(command,1,7);
    return sendCommand.getData();
  }
}
