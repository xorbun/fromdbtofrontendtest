package brunocapobianco.fromdbtofrontendtest.Payloads;

import brunocapobianco.fromdbtofrontendtest.Entities.User;

import java.util.UUID;

public record NewBlogDTO(String categoria, String titolo, String contenuto, int tempodilettura,String cover) {
}
