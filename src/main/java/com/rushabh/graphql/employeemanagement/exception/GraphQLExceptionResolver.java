package com.rushabh.graphql.employeemanagement.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        GraphqlErrorBuilder<?> errorBuilder = GraphqlErrorBuilder.newError();
        errorBuilder.path(env.getExecutionStepInfo().getPath());

        if (ex instanceof EmployeeManagementException) {
            errorBuilder.
                    message(ex.getMessage())
                    .extensions(Map.of("status", ((EmployeeManagementException) ex).getHttpStatus()))
                    .errorType(ErrorType.DataFetchingException);
            return errorBuilder.build();
        }

        if (ex instanceof Exception) {
            errorBuilder.
                    message("Technical Error").
                    extensions(Map.of("status", HttpStatus.INTERNAL_SERVER_ERROR)).
                    errorType(ErrorType.ExecutionAborted);
            return errorBuilder.build();
        }
        return super.resolveToSingleError(ex, env);
    }
}
