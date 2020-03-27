package lan.qxc.lightserver.netty.attribute;

import io.netty.util.AttributeKey;
import lan.qxc.lightserver.netty.session.Session;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");


}
