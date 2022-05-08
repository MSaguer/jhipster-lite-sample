package tech.jhipster.fullapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.when;
import static tech.jhipster.fullapp.FullappApp.LF;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@UnitTest
@ExtendWith(SpringExtension.class)
class FullappAppTest {

  @Mock
  ConfigurableApplicationContext applicationContext;

  @Mock
  ConfigurableEnvironment environment;

  @Test
  void shouldConstruct() {
    assertThatCode(() -> new FullappApp()).doesNotThrowAnyException();
  }

  @Test
  void shouldMain() {
    try (MockedStatic<SpringApplication> springApplication = Mockito.mockStatic(SpringApplication.class)) {
      when(applicationContext.getEnvironment()).thenReturn(environment);
      springApplication.when(() -> SpringApplication.run(FullappApp.class, new String[] {})).thenReturn(applicationContext);

      assertThatCode(() -> FullappApp.main(new String[] {})).doesNotThrowAnyException();
    }
  }

  @Test
  void shouldApplicationRunning() {
    String result = FullappApp.applicationRunning("jhlite");
    assertThat(result).isEqualTo("  Application 'jhlite' is running!" + LF);
  }

  @Test
  void shouldAccessUrlLocalWithoutServerPort() {
    String result = FullappApp.accessUrlLocal(null, null, null);
    assertThat(result).isEmpty();
  }

  @Test
  void shouldAccessUrlLocalWithoutContextPath() {
    String result = FullappApp.accessUrlLocal("http", "8080", "/");
    assertThat(result).isEqualTo("  Local: \thttp://localhost:8080/" + LF);
  }

  @Test
  void shouldAccessUrlLocalWithContextPath() {
    String result = FullappApp.accessUrlLocal("http", "8080", "/lite/");
    assertThat(result).isEqualTo("  Local: \thttp://localhost:8080/lite/" + LF);
  }

  @Test
  void shouldAccessUrlExternalWithoutServerPort() {
    String result = FullappApp.accessUrlExternal(null, null, null, null);
    assertThat(result).isEmpty();
  }

  @Test
  void shouldAccessUrlExternalWithoutContextPath() {
    String result = FullappApp.accessUrlExternal("http", "127.0.1.1", "8080", "/");
    assertThat(result).isEqualTo("  External: \thttp://127.0.1.1:8080/" + LF);
  }

  @Test
  void shouldAccessUrlExternalWithContextPath() {
    String result = FullappApp.accessUrlExternal("http", "127.0.1.1", "8080", "/lite/");
    assertThat(result).isEqualTo("  External: \thttp://127.0.1.1:8080/lite/" + LF);
  }

  @Test
  void shouldConfigServerWithoutConfigServerStatus() {
    String result = FullappApp.configServer(null);
    assertThat(result).isEmpty();
  }

  @Test
  void shouldConfigServer() {
    String result = FullappApp.configServer("Connected to the JHipster Registry running in Docker");
    assertThat(result).contains("Config Server: Connected to the JHipster Registry running in Docker");
  }

  @Test
  void shouldGetProtocol() {
    assertThat(FullappApp.getProtocol(null)).isEqualTo("http");
  }

  @Test
  void shouldGetProtocolForBlank() {
    assertThat(FullappApp.getProtocol(" ")).isEqualTo("https");
  }

  @Test
  void shouldGetProtocolForValue() {
    assertThat(FullappApp.getProtocol("https")).isEqualTo("https");
  }

  @Test
  void shouldGetContextPath() {
    assertThat(FullappApp.getContextPath("/chips")).isEqualTo("/chips");
  }

  @Test
  void shouldGetContextPathForNull() {
    assertThat(FullappApp.getContextPath(null)).isEqualTo("/");
  }

  @Test
  void shouldGetContextPathForBlank() {
    assertThat(FullappApp.getContextPath(" ")).isEqualTo("/");
  }

  @Test
  void shouldGetHost() {
    assertThatCode(FullappApp::getHostAddress).doesNotThrowAnyException();
  }

  @Test
  void shouldGetHostWithoutHostAddress() {
    try (MockedStatic<InetAddress> inetAddress = Mockito.mockStatic(InetAddress.class)) {
      inetAddress.when(InetAddress::getLocalHost).thenThrow(new UnknownHostException());

      String result = FullappApp.getHostAddress();

      assertThat(result).isEqualTo("localhost");
    }
  }
}
