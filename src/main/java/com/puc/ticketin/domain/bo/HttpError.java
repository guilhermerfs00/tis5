package com.puc.ticketin.domain.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class HttpError {
    @Schema(
            description = "Timestamp que ocorreu o erro."
    )
    private LocalDateTime timestamp;
    @Schema(
            description = "Código do Http Status."
    )
    private int status;
    @Schema(
            description = "Mensagem do Http Status."
    )
    private String title;
    @Schema(
            description = "Path chamado que ocasionou o erro."
    )
    private String path;
    @Schema(
            description = "Mensagem tratada para o usuário."
    )
    private String detailMessage;
    @Schema(
            description = "Código da mensagem tratada para o usuário."
    )
    private int detailMessageCode;
    @Schema(
            description = "Lista de objetos ou campos que geraram o erro (opcional)"
    )
    private List<Notification> notifications;

    public static HttpErrorBuilder builder() {
        return new HttpErrorBuilder();
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public int getStatus() {
        return this.status;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPath() {
        return this.path;
    }

    public String getDetailMessage() {
        return this.detailMessage;
    }

    public int getDetailMessageCode() {
        return this.detailMessageCode;
    }

    public List<Notification> getNotifications() {
        return this.notifications;
    }

    public HttpError() {
    }

    public HttpError(LocalDateTime timestamp, int status, String title, String path, String detailMessage, int detailMessageCode, List<Notification> notifications) {
        this.timestamp = timestamp;
        this.status = status;
        this.title = title;
        this.path = path;
        this.detailMessage = detailMessage;
        this.detailMessageCode = detailMessageCode;
        this.notifications = notifications;
    }

    public static class HttpErrorBuilder {
        private LocalDateTime timestamp;
        private int status;
        private String title;
        private String path;
        private String detailMessage;
        private int detailMessageCode;
        private List<Notification> notifications;

        HttpErrorBuilder() {
        }

        public HttpErrorBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public HttpErrorBuilder status(int status) {
            this.status = status;
            return this;
        }

        public HttpErrorBuilder title(String title) {
            this.title = title;
            return this;
        }

        public HttpErrorBuilder path(String path) {
            this.path = path;
            return this;
        }

        public HttpErrorBuilder detailMessage(String detailMessage) {
            this.detailMessage = detailMessage;
            return this;
        }

        public HttpErrorBuilder detailMessageCode(int detailMessageCode) {
            this.detailMessageCode = detailMessageCode;
            return this;
        }

        public HttpErrorBuilder notifications(List<Notification> notifications) {
            this.notifications = notifications;
            return this;
        }

        public HttpError build() {
            return new HttpError(this.timestamp, this.status, this.title, this.path, this.detailMessage, this.detailMessageCode, this.notifications);
        }

        public String toString() {
            return "HttpError.HttpErrorBuilder(timestamp=" + this.timestamp + ", status=" + this.status + ", title=" + this.title + ", path=" + this.path + ", detailMessage=" + this.detailMessage + ", detailMessageCode=" + this.detailMessageCode + ", notifications=" + this.notifications + ")";
        }
    }

    @Schema(
            description = "Notification"
    )
    public static class Notification {
        @Schema(
                example = "Título da notificação"
        )
        private String title;
        @Schema(
                example = "Conteúdo/detalhamento da notificação"
        )
        private String content;
        @Schema(
                example = "ERROR"
        )
        private TypeNotitification type;

        public Notification() {
        }

        public Notification(String title, String content, TypeNotitification type) {
            this.title = title;
            this.content = content;
            this.type = type;
        }

        public static NotificationBuilder builder() {
            return new NotificationBuilder();
        }

        public String getTitle() {
            return this.title;
        }

        public String getContent() {
            return this.content;
        }

        public TypeNotitification getType() {
            return this.type;
        }

        public static class NotificationBuilder {
            private String title;
            private String content;
            private TypeNotitification type;

            NotificationBuilder() {
            }

            public NotificationBuilder title(String title) {
                this.title = title;
                return this;
            }

            public NotificationBuilder content(String content) {
                this.content = content;
                return this;
            }

            public NotificationBuilder type(TypeNotitification type) {
                this.type = type;
                return this;
            }

            public Notification build() {
                return new Notification(this.title, this.content, this.type);
            }

            public String toString() {
                return "HttpError.Notification.NotificationBuilder(title=" + this.title + ", content=" + this.content + ", type=" + this.type + ")";
            }
        }
    }

    public static enum TypeNotitification {
        ERROR,
        ALERT,
        NULL;

        private TypeNotitification() {
        }
    }
}