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

package uk.co.bubblebearapps.motionaiclient.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by joefr_000 on 09/02/2017.
 */

public class StringUtils {

    private StringUtils() {
    }

    /**
     * Splits this string around matches of the given <a
     * href="../util/regex/Pattern.html#sum">regular expression</a>.
     * <p>
     * <p> The string <tt>"Blah blah [img]some image[/img] blah blah"</tt>, for example, yields the following
     * results with these expressions:
     * <p>
     * <blockquote><table cellpadding=1 cellspacing=0 summary="Split examples showing regex and result">
     * <tr>
     * <th>Regex</th>
     * <th>Result</th>
     * </tr>
     * <tr><td align=center>\[(img|video|youtube)\]([\s\S]+?)\[/\1\]</td>
     * <td><tt>{ "Blah blah ", "[img]some image[/img]", " blah blah" }</tt></td></tr>
     * </table></blockquote>
     *
     * @param responseText the string to be split
     * @param regex        the delimiting regular expression
     * @throws PatternSyntaxException if the regular expression's syntax is invalid*
     * @see String#split(String)
     */
    public static List<String> splitButKeep(String responseText, String regex) {

        Matcher matcher = Pattern.compile(regex).matcher(responseText);

        ArrayList<String> result = new ArrayList<>();

        int trailingIndex = 0;
        while (matcher.find()) {
            if (matcher.start() > trailingIndex) {
                //found plain text
                String substring = responseText.substring(trailingIndex, matcher.start());
                result.add(substring);

            }
            // found match
            String match = responseText.substring(matcher.start(), matcher.end());
            result.add(match);

            trailingIndex = matcher.end();
        }

        if (trailingIndex < responseText.length()) {
            // found plain text
            String substring = responseText.substring(trailingIndex, responseText.length());
            result.add(substring);
        }

        return result;


    }
}
