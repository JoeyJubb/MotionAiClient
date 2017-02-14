/*
 * Copyright 2017 Bubblebear Apps Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.bubblebearapps.samplebot;


import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexValidator {

    private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    private static Pattern pattern;
    private static Matcher matcher;

    static {
        pattern = Pattern.compile(HEX_PATTERN);
    }

    private HexValidator() {
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean validate(final String hex) {

        if (Strings.isNullOrEmpty(hex)) {
            return false;
        }

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}