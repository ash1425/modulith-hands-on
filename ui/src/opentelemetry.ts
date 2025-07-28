import {WebTracerProvider} from '@opentelemetry/sdk-trace-web'
import {getWebAutoInstrumentations} from '@opentelemetry/auto-instrumentations-web'
import {SimpleSpanProcessor} from '@opentelemetry/sdk-trace-base'
import {registerInstrumentations} from '@opentelemetry/instrumentation'
import {ZoneContextManager} from '@opentelemetry/context-zone'
import {OTLPTraceExporter} from '@opentelemetry/exporter-trace-otlp-http';
import {W3CTraceContextPropagator} from '@opentelemetry/core';
import {resourceFromAttributes} from '@opentelemetry/resources';

export const setupOpenTelemetry = () => {
    const exporter = new OTLPTraceExporter();

    const resource = resourceFromAttributes({
        'service.name': 'my-web-app',
        'service.version': '1.0.0',
    });

    const provider = new WebTracerProvider({
        resource,
        spanProcessors: [
            new SimpleSpanProcessor(exporter),
        ],
    });
    provider.register({
        contextManager: new ZoneContextManager(),
        propagator: new W3CTraceContextPropagator(),
    });

    registerInstrumentations({
        instrumentations: [
            getWebAutoInstrumentations(),
        ],
    });
    console.log('OpenTelemetry initialized');
}