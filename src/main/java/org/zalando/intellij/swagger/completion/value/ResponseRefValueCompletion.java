package org.zalando.intellij.swagger.completion.value;

import com.intellij.codeInsight.completion.CompletionResultSet;
import org.zalando.intellij.swagger.completion.value.model.StringValue;
import org.zalando.intellij.swagger.completion.value.model.Value;
import org.zalando.intellij.swagger.traversal.PositionResolver;

import java.util.List;
import java.util.stream.Collectors;

class ResponseRefValueCompletion extends ValueCompletion {

    protected ResponseRefValueCompletion(final PositionResolver positionResolver, final CompletionResultSet completionResultSet) {
        super(positionResolver, completionResultSet);
    }

    @Override
    public void fill() {
        getResponseKeys().stream().forEach(this::addValue);
    }

    private List<Value> getResponseKeys() {
        return positionResolver.getKeyNamesOf("responses").stream()
                .map(keyName -> "#/responses/" + keyName)
                .map(StringValue::new)
                .collect(Collectors.toList());
    }
}