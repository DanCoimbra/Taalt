package gameserver;

public interface IOutputProducer {
    public void addOutputReceiver(IOutputReceiver outputReceiver);
    public void clearOutputReceiverList();
    public void fireOutput();
}
