package gameclient;

import gameserver.IInputProducer;
import gameserver.IOutputReceiver;

/** Interface acumuladora que o servidor usará para interagir com o cliente. */
public interface IClient extends IInputProducer, IOutputReceiver {
}
