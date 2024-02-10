package org.acme.springkafkastreamsdemo;

import static org.apache.kafka.streams.StreamsConfig.*;

import org.apache.kafka.common.serialization.Serdes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
// @EnableKafkaStreams
public class KafkaStreamsConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    // @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    @Bean(name = "kafkaStreamsConfigA")
    KafkaStreamsConfiguration kafkaStreamsConfigA() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, "streams-app-a");
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        return new KafkaStreamsConfiguration(props);
    }

    @Bean(name = "kafkaStreamsConfigB")
    KafkaStreamsConfiguration kafkaStreamsConfigB() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, "streams-app-b");
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        return new KafkaStreamsConfiguration(props);
    }

    @Bean(name = "streamsBuilderA")
    StreamsBuilderFactoryBean streamsBuilderA(KafkaStreamsConfiguration kafkaStreamsConfigA) {
        return new StreamsBuilderFactoryBean(kafkaStreamsConfigA);
    }

    @Bean(name = "streamsBuilderB")
    StreamsBuilderFactoryBean streamsBuilderB(KafkaStreamsConfiguration kafkaStreamsConfigB) {
        return new StreamsBuilderFactoryBean(kafkaStreamsConfigB);
    }
}
