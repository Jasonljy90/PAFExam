package Jason.Workshop3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;

@Service
public class LogAuditService {
    private static final String CONTACT_ENTITY = "transaction";

    @Autowired
    RedisTemplate<String, JsonObject> redisTemplate;

    public void save(final JsonObject jsonObj) {
        redisTemplate.opsForHash()
                .put(CONTACT_ENTITY + "_Map", jsonObj.getString("transactionId"), jsonObj);
    }
}
