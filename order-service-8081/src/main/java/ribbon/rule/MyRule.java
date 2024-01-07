package ribbon.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description
 *
 * @author calm
 * */
@Configuration
public class MyRule {
    @Bean
    public IRule randomRule(){
        return new RandomRule();
    }
}
