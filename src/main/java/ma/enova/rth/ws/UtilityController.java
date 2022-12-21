package ma.enova.rth.ws;

import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.MyDate;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.MD5Checksum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@RestController
public class UtilityController extends BaseController {

    @Value("${uploads.location.temp}")
    private String uploadedTempFolder;

    @GetMapping(value = "/utility/getCurrentDate")
    public ResponseEntity<Long> getCurrentDate() {
        return new ResponseEntity<Long>(new Date().getTime(), HttpStatus.OK);

    }

    @GetMapping(value = "/utility/getCurrentDateString")
    public ResponseEntity<String> getCurrentDateString() {
        return new ResponseEntity<String>(DateUtil.dateToString(LocalDate.now()), HttpStatus.OK);

    }

    @GetMapping(value = "/utility/getMyDate")
    public ResponseEntity<MyDate> getMyDate() {

        return new ResponseEntity<MyDate>(new MyDate(new Date().getTime(), Locale.getDefault().toString(), TimeZone.getDefault().getID()), HttpStatus.OK);

    }

    @GetMapping(value = "/utility/getCurrentDateTimeString")
    public ResponseEntity<String> getCurrentDateTimeString() {
        return new ResponseEntity<String>(DateUtil.dateTimeToString(LocalDateTime.now()), HttpStatus.OK);

    }

    @PostMapping(value = "/utility/uploadFile", consumes = "multipart/form-data")
    public @ResponseBody ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) throws Exception {
        String chechsum = "";
        if (file != null) {
            File convFile = new File(uploadedTempFolder + file.getOriginalFilename());
            if (!convFile.getParentFile().exists())
                convFile.getParentFile().mkdirs();
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();

            chechsum = MD5Checksum.getMD5Checksum(convFile.getPath());
            return new ResponseEntity<String>(chechsum, HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);

    }

}