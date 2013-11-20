package de.unima.sempoi.server.adapter.dbpedia;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TypesTest {
	
	@Test
	public void testTypes() {
		InputStream types = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("types.json");
		
		List<TestType> data = new Gson().fromJson(new InputStreamReader(types),
				new TypeToken<List<TestType>>(){}.getType());
		
		for(TestType type : data) {
			System.out.println(Types.cleanTypeName(type.getIn()));
//			assertEquals("It should clean the type names properly",
//					type.getOut(), Types.cleanTypeName(type.getIn()));
		}
	}

}
