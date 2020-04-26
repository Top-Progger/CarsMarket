package com.api.carmarket.utils.validator.impl;

import com.api.carmarket.utils.validator.Validator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;

@Component
@AllArgsConstructor
public class JsonSchemaValidator implements Validator {
    @Autowired
    private final ObjectMapper objectMapper;

    @Override
    public boolean validate(Object data, String path) {
        boolean isValid = false;

        try {
            String dataString = objectMapper.writeValueAsString(data);

            File jsonSchemaFile = ResourceUtils.getFile(path);

            JsonNode schemaNode = JsonLoader.fromFile(jsonSchemaFile);
            JsonNode dataNode = JsonLoader.fromString(dataString);

            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(schemaNode);

            ProcessingReport report = schema.validate(dataNode);

            System.out.println("Report:" + " " + report.toString());

            if (report.isSuccess()){
                isValid = true;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return isValid;
    }
}
