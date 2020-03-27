package lan.qxc.lightserver.netty.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.AttributeKey;
import lan.qxc.lightserver.netty.attribute.Attributes;
import lan.qxc.lightserver.netty.session.Session;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static Map<Long, Channel> userIdChannelMap = new ConcurrentHashMap<>();
    private static final Map<Long, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session,Channel channel){
        userIdChannelMap.put(session.getUserid(),channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel){
        if(hasLogin(channel)){
            Session session = getSession(channel);
            userIdChannelMap.remove(session.getUserid());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel){
        if(channel==null){
            return false;
        }
        return getSession(channel)!=null;
    }


    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }


    public static Channel getChannel(Long userid){
        return userIdChannelMap.get(userid);
    }


    public static void bindChannelGroup(Long groupId, ChannelGroup channelGroup) {
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(Long groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }

}
