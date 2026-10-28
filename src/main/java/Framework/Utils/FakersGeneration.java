package Framework.Utils;

import java.util.Locale;

import net.datafaker.Faker;

public class FakersGeneration {

    private Faker faker;

    public FakersGeneration() {
        faker = new Faker(Locale.of("pt", "BR"));
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }
}
