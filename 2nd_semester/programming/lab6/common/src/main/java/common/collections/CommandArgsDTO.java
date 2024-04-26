package common.collections;

import common.commands.Command;
import lombok.Getter;

public record CommandArgsDTO(Command command, String[] args) {
}
