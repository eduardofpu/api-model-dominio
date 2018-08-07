package br.com.error;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
    private String title;
    private int status;
    private String detail;
    private Long timestamp;
    private String developerMessage;
    private String field;
    private String fieldMessage;
}
