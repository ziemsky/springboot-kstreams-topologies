package org.acme.springkafkastreamsdemo;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DemoTopologyBuilder {

    private final Logger log = LoggerFactory.getLogger(DemoTopologyBuilder.class);

    @Autowired
    void topologyA(@Qualifier("streamsBuilderA") StreamsBuilder streamsBuilderA) {
        log.info("topologyA: {}", streamsBuilderA);
        streamsBuilderA
            .stream("topic-a", Consumed.with(Serdes.String(), Serdes.String()))
            .peek((key, value) -> log.info("Peek topic-a: {} : {}", key, value))
            .foreach((key, value) -> log.info("Consuming topic-a: {} : {}", key, value));

        log.info(streamsBuilderA.build().describe().toString());
    }

    @Autowired
    void topologyB(@Qualifier("streamsBuilderB") StreamsBuilder streamsBuilderB) {
        log.info("topologyB: {}", streamsBuilderB);

        streamsBuilderB
            .stream("topic-b", Consumed.with(Serdes.String(), Serdes.String()))
            .foreach((key, value) -> log.info("Consuming topic-b: {} : {}", key, value));

        log.info(streamsBuilderB.build().describe().toString());
    }

}
