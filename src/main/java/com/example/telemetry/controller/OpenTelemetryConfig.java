package com.example.telemetry.controller;

import com.azure.monitor.opentelemetry.exporter.AzureMonitorExporterBuilder;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;

public class OpenTelemetryConfig {

	public static void setupOpenTelemetry() {
		// Azure Monitor Connection String (Replace with your Instrumentation Key)
		String connectionString = "InstrumentationKey=YOUR_INSTRUMENTATION_KEY"; // Replace with your actual key

		// Create the Azure Monitor Trace Exporter
		SpanExporter azureMonitorExporter = new AzureMonitorExporterBuilder().connectionString(connectionString)
				.buildExporter();

		// Create and configure the SDK
		OpenTelemetry tracerProvider = SdkTracerProvider.builder()
				.addSpanProcessor(SimpleSpanProcessor.create(azureMonitorExporter)).build();

		// Set the global OpenTelemetry SDK
		GlobalOpenTelemetry.set(tracerProvider);
	}
}
