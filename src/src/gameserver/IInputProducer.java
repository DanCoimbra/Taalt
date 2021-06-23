package gameserver;

public interface IInputProducer {
    public void addInputReceiver(IInputReceiver inputReceiver);
    public void fireInput(Input input);
}
