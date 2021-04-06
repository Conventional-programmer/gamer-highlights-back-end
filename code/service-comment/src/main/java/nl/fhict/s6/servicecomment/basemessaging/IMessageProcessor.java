package nl.fhict.s6.servicecomment.basemessaging;

public interface IMessageProcessor {
    void processMessage(String type, String data);

}
