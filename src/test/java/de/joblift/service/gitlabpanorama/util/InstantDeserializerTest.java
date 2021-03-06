package de.joblift.service.gitlabpanorama.util;

import static de.galan.commons.time.Instants.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.time.Instant;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


/**
 * CUT InstantDeserializer
 */
public class InstantDeserializerTest {

	@Test
	public void standardUtcApiFormat() throws JsonParseException, JsonMappingException, IOException {
		String input = "{\"created\":\"2019-03-26T08:47:57Z\"}";
		Dummy output = Mapper.get().readValue(input, Dummy.class);
		assertThat(output.created).isEqualTo(instantUtc("2019-03-26T08:47:57Z"));
	}


	@Test
	public void standardUtcWebhookFormat() throws JsonParseException, JsonMappingException, IOException {
		String input = "{\"created\":\"2019-03-26 08:47:57 UTC\"}";
		Dummy output = Mapper.get().readValue(input, Dummy.class);
		assertThat(output.created).isEqualTo(instantUtc("2019-03-26T08:47:57Z"));
	}


	@Test
	public void offsetUtcFormat() throws JsonParseException, JsonMappingException, IOException {
		String input = "{\"created\":\"2018-12-19T19:43:57.350+11:00\"}";
		Dummy output = Mapper.get().readValue(input, Dummy.class);
		assertThat(output.created).isEqualTo(instantUtc("2018-12-19T08:43:57.350Z"));
	}

}


class Dummy {

	@JsonProperty
	Instant created;

}
