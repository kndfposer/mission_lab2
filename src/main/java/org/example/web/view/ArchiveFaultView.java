package org.example.web.view;

import java.time.LocalDateTime;

public record ArchiveFaultView(LocalDateTime timestamp, int status, String error, String message, String path) {}

