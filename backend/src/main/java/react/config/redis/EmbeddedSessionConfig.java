package react.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.SessionRepositoryFilter;

@Profile("fake-redis")
@Configuration
public class EmbeddedSessionConfig {

  @Bean
  public SessionRepositoryFilter<ExpiringSession> springSessionRepositoryFilter() {
    SessionRepositoryFilter<ExpiringSession> sessionRepositoryFilter = new SessionRepositoryFilter<ExpiringSession>(new MapSessionRepository());
    sessionRepositoryFilter.setHttpSessionStrategy( new HeaderHttpSessionStrategy());
    return sessionRepositoryFilter;
  }
}