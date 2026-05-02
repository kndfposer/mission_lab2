package org.example.web;

import org.example.model.Mission;
import org.example.service.ArchiveIntakeService;
import org.example.service.ArchiveShelfService;
import org.example.service.ReportDeckService;
import org.example.web.adapter.MissionCardAssembler;
import org.example.web.view.MissionRowView;
import org.example.web.view.MissionSnapshotView;
import org.example.web.view.ReportBuildRequest;
import org.example.web.view.UploadTicketView;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/hub")
public class MissionHubController {
    private final ArchiveIntakeService archiveIntakeService;
    private final ArchiveShelfService archiveShelfService;
    private final ReportDeckService reportDeckService;

    public MissionHubController(ArchiveIntakeService archiveIntakeService,
                                ArchiveShelfService archiveShelfService,
                                ReportDeckService reportDeckService) {
        this.archiveIntakeService = archiveIntakeService;
        this.archiveShelfService = archiveShelfService;
        this.reportDeckService = reportDeckService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadTicketView upload(@RequestPart("file") MultipartFile file) throws Exception {
        ArchiveIntakeService.IntakeBundle bundle = archiveIntakeService.parseAndCheck(file);
        Mission saved = archiveShelfService.saveFresh(bundle.mission());
        return new UploadTicketView("Миссия успешно сохранена в архив", saved.getMissionId(), bundle.format());
    }

    @GetMapping
    public List<MissionRowView> list() {
        return archiveShelfService.fetchAll().stream()
                .map(MissionCardAssembler::toRow)
                .toList();
    }

    @GetMapping("/{missionId}")
    public MissionSnapshotView one(@PathVariable String missionId) {
        return MissionCardAssembler.toSnapshot(archiveShelfService.fetchOneDetailed(missionId));
    }

    @PostMapping(value = "/report", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String report(@RequestBody ReportBuildRequest request) {
        if (request.missionId() == null || request.missionId().isBlank()) {
            throw new IllegalArgumentException("missionId обязателен");
        }

        String type = request.type() == null || request.type().isBlank()
                ? "summary"
                : request.type();

        return reportDeckService.render(
                archiveShelfService.fetchOneDetailed(request.missionId()),
                type
        );
    }

    @DeleteMapping("/{missionId}")
    public String delete(@PathVariable String missionId) {
        archiveShelfService.removeOne(missionId);
        return "Миссия " + missionId + " удалена из архива";
    }
}