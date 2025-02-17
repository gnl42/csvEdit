/* Copyright 2011 csvedit
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
package me.glindholm.plugin.csvedit2.customeditor.model;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import me.glindholm.plugin.csvedit2.model.AbstractCSVFile;
import me.glindholm.plugin.csvedit2.model.ICsvOptionsProvider;

/**
 *
 * {@link DefaultCSVFile} implements the {@link AbstractCSVFile} abstract methods based on the
 * values stored in the preferences system
 *
 * @author jpizar
 * @author msavy
 *
 */
public class DefaultCSVFile extends AbstractCSVFile {

    /** Preferences provider */
    private final ICsvOptionsProvider optionsProvider;

    /**
     * Constructor
     *
     * @param provider the {@link PreferencesCSVOptionsProvider}
     */
    public DefaultCSVFile(final ICsvOptionsProvider provider) {
        optionsProvider = provider;
    }

    @Override
    public boolean isFirstLineHeader() {
        return optionsProvider.getUseFirstLineAsHeader();
    }

    @Override
    public boolean getSensitiveSearch() {
        return optionsProvider.getSensitiveSearch();
    }

    @Override
    public char getCustomDelimiter() {
        return optionsProvider.getCustomDelimiter().charAt(0);
    }

    @Override
    public char getCommentChar() {
        final String commentChar = optionsProvider.getCommentChar();
        char result = Character.UNASSIGNED;
        if (commentChar != null && commentChar != "") {
            result = commentChar.charAt(0);
        }
        return result;
    }

    @Override
    public char getTextQualifier() {
        final String qualifierChar = optionsProvider.getTextQualifier();
        char result = Character.UNASSIGNED;
        if (qualifierChar != null && qualifierChar != "" && qualifierChar.length() > 0) {
            result = qualifierChar.charAt(0);
        }
        return result;
    }

    @Override
    public boolean useQualifier() {
        return optionsProvider.useTextQualifier();
    }

    @Override
    public String getInCellDelimiter() {
        return optionsProvider.getInCellDelimiter();
    }

    @Override
    public String getRegexTableMarker() {

        String result = "";

        try {
            Pattern.compile(optionsProvider.getRegexTableMarker());
            result = optionsProvider.getRegexTableMarker();
        } catch (final PatternSyntaxException exception) {

        }

        return result;
    }
}