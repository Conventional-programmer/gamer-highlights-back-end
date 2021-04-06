package nl.fhict.s6.servicecomment.basemessaging;

public interface IEncapsulatingMessageGenerator {
    <T> EncapsulatingMessage generateMessage(T content);

    <T> String generateMessageString(T content);
}
