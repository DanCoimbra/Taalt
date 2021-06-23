package gameserver;

import java.awt.*;

/**
 *  Interface para objetos que contém, de alguma forma, produtores de
 *  conteúdo (IContentProducer) dentro de si e permitem que agentes
 *  externos obtenham acesso a eles.
 */
public interface IContentProducerViewer {
    public IContentProducer getContentProducer(Point pos);
}
