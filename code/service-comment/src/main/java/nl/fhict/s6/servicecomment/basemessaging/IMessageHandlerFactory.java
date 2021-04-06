package nl.fhict.s6.servicecomment.basemessaging;

public interface IMessageHandlerFactory {
    IMessageHandler getHandler(String simpleName);
}
