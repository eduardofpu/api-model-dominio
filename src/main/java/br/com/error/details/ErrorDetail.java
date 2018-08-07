package br.com.error.details;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private Long timestamp;
    private String developerMessage;
    private String field;
    private String fieldMessage;
}
