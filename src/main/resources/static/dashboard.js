const panes = document.querySelectorAll(".pane");

document.querySelectorAll(".sidebar button[data-target]").forEach(btn => {
    btn.addEventListener("click", () => {
        panes.forEach(p => p.classList.remove("active"));
        document.getElementById(btn.dataset.target).classList.add("active");
    });
});

const missionFile = document.getElementById("missionFile");
const uploadForm = document.getElementById("uploadForm");
const uploadResult = document.getElementById("uploadResult");

const missionCards = document.getElementById("missionCards");
const refreshArchiveBtn = document.getElementById("refreshArchiveBtn");

const detailMissionId = document.getElementById("detailMissionId");
const loadDetailBtn = document.getElementById("loadDetailBtn");
const detailResult = document.getElementById("detailResult");

const reportMissionId = document.getElementById("reportMissionId");
const reportType = document.getElementById("reportType");
const loadReportBtn = document.getElementById("loadReportBtn");
const reportResult = document.getElementById("reportResult");

const deleteMissionId = document.getElementById("deleteMissionId");
const deleteBtn = document.getElementById("deleteBtn");
const deleteResult = document.getElementById("deleteResult");

function pretty(data) {
    return typeof data === "string" ? data : JSON.stringify(data, null, 2);
}

async function readResponse(response) {
    const text = await response.text();
    if (!text) return null;

    const type = response.headers.get("content-type") || "";
    if (type.includes("application/json")) {
        return JSON.parse(text);
    }
    return text;
}

function errorText(data) {
    if (!data) return "Неизвестная ошибка";
    if (typeof data === "string") return data;
    return data.message || JSON.stringify(data, null, 2);
}

uploadForm.addEventListener("submit", async e => {
    e.preventDefault();

    if (!missionFile.files.length) {
        uploadResult.textContent = "Выберите файл";
        return;
    }

    const formData = new FormData();
    formData.append("file", missionFile.files[0]);

    try {
        const response = await fetch("/api/hub/upload", {
            method: "POST",
            body: formData
        });

        const data = await readResponse(response);

        if (!response.ok) {
            uploadResult.textContent = errorText(data);
            return;
        }

        uploadResult.textContent = pretty(data);
        loadArchive();
    } catch {
        uploadResult.textContent = "Ошибка загрузки файла";
    }
});

async function loadArchive() {
    try {
        const response = await fetch("/api/hub");
        const data = await readResponse(response);

        if (!response.ok) {
            missionCards.textContent = errorText(data);
            return;
        }

        missionCards.innerHTML = "";

        if (!Array.isArray(data) || data.length === 0) {
            missionCards.textContent = "Архив пуст";
            return;
        }

        data.forEach(item => {
            const card = document.createElement("div");
            card.className = "card";
            card.innerHTML = `
                <strong>${item.missionId}</strong>

                Дата: ${item.date ?? "-"}

                Локация: ${item.location ?? "-"}

                Результат: ${item.outcome ?? "-"}
            `;

            card.addEventListener("click", () => {
                detailMissionId.value = item.missionId;
                reportMissionId.value = item.missionId;
                deleteMissionId.value = item.missionId;

                panes.forEach(p => p.classList.remove("active"));
                document.getElementById("detailPane").classList.add("active");

                loadDetails();
            });

            missionCards.appendChild(card);
        });
    } catch {
        missionCards.textContent = "Ошибка получения архива";
    }
}

async function loadDetails() {
    const missionId = detailMissionId.value.trim();


if (!missionId) {
        detailResult.textContent = "Введите missionId";
        return;
    }

    try {
        const response = await fetch(`/api/hub/${encodeURIComponent(missionId)}`);
        const data = await readResponse(response);

        if (!response.ok) {
            detailResult.textContent = errorText(data);
            return;
        }

        detailResult.textContent = pretty(data);
    } catch {
        detailResult.textContent = "Ошибка чтения миссии";
    }
}

async function loadReport() {
    const missionId = reportMissionId.value.trim();

    if (!missionId) {
        reportResult.textContent = "Введите missionId";
        return;
    }

    try {
        const response = await fetch("/api/hub/report", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                missionId: missionId,
                type: reportType.value
            })
        });

        const data = await readResponse(response);

        if (!response.ok) {
            reportResult.textContent = errorText(data);
            return;
        }

        reportResult.textContent = pretty(data);
    } catch {
        reportResult.textContent = "Ошибка формирования отчёта";
    }
}

async function deleteMission() {
    const missionId = deleteMissionId.value.trim();

    if (!missionId) {
        deleteResult.textContent = "Введите missionId";
        return;
    }

    const confirmed = confirm(`Удалить миссию ${missionId}?`);
    if (!confirmed) return;

    try {
        const response = await fetch(`/api/hub/${encodeURIComponent(missionId)}`, {
            method: "DELETE"
        });

        const data = await readResponse(response);

        if (!response.ok) {
            deleteResult.textContent = errorText(data);
            return;
        }

        deleteResult.textContent = pretty(data);
        detailResult.textContent = "";
        reportResult.textContent = "";
        loadArchive();
    } catch {
        deleteResult.textContent = "Ошибка удаления";
    }
}

refreshArchiveBtn.addEventListener("click", loadArchive);
loadDetailBtn.addEventListener("click", loadDetails);
loadReportBtn.addEventListener("click", loadReport);
deleteBtn.addEventListener("click", deleteMission);

loadArchive();


