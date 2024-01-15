package hello.upload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {

    private String uploadFileName; // 사용자가 업로드한 파일명
    private String storeFileName; // 저장될 파일명(서부 내부에서 관리하는 파일명)
}
