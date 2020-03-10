package lan.qxc.lightserver.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sun.corba.se.impl.oa.toa.TOA;
import lan.qxc.lightserver.entity.User;

import java.util.Date;

public class JWTUtils {

    private static final String SECRET = "abcdegasd2564265";
    //过期时间
    private static final Long EXPIER = 24*60*1000L;

    public static String getToken(User user){

        String token = JWT.create().withAudience(user.getUserid()+"")
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIER))
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static  boolean verifyToken(String token){
        if(token==null||token.isEmpty()){
            return false;
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
//            throw new RuntimeException("401");
            return false;
        }

        return true;

    }

    public static int getUserid(String token){
        if(!verifyToken(token)){
            return -1;
        }
        return Integer.parseInt(JWT.decode(token).getAudience().get(0));
    }



}
