package jvm.pablohdz.courseapplication.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import javax.validation.constraints.NotNull;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class UrlUtils {

    /**
     * Creates a {@link URI} object with the current URL
     *
     * @param complementaryPath A string with the path to add ej: "/api/some"
     * @return An {@link URI} object with the established URL
     */
    public static URI createUrl(@NotNull String complementaryPath) {
        return URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(complementaryPath)
                .toUriString());
    }
}
