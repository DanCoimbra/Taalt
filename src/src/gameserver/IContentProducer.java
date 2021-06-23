package gameserver;

public interface IContentProducer {
    public void addContentReceiver(IContentReceiver contentReceiver);
    public void alertContentUpdate();
    public PlayerID sendContent();
}
