package ge.workshops.workshop1.dto;

import lombok.Data;

@Data
public class PostJP {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
