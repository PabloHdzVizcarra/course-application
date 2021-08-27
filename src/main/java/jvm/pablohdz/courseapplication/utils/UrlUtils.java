package jvm.pablohdz.courseapplication.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class UrlUtils {

    public static URI createUrl(String complementaryPath) {
        return URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(complementaryPath)
                .toUriString());
    }
}
