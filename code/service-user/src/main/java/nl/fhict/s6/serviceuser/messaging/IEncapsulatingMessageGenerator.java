package nl.fhict.s6.serviceuser.messaging;

public interface IEncapsulatingMessageGenerator {
    <T> EncapsulatingMessage generateMessage(T content);

    <T> String generateMessageString(T content);
}
