package client.collections;

import common.commands.Command;

public record CommandArgsDTO(Command command, String[] args) {
}
