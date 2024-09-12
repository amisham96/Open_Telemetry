package com.example.telemetry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

@RestController
public class TelemetryController {

	private final Tracer tracer;

	@Autowired
	public TelemetryController(OpenTelemetry openTelemetry) {
		this.tracer = openTelemetry.getTracer("my-app");
	}

	@GetMapping("/example")
	public String example() {
		Span span = tracer.spanBuilder("example-span").startSpan();
		try (Scope scope = span.makeCurrent()) {
			// Your business logic here
			span.setAttribute("Home Name", "Chitti");
			return "Hello, World!";
		} finally {
			span.end();
		}
	}
}