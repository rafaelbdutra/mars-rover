package br.com.rbdutra.mars.rover;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import br.com.rbdutra.mars.rover.domain.Command;
import br.com.rbdutra.mars.rover.domain.Position;
import br.com.rbdutra.mars.rover.domain.Rover;
import br.com.rbdutra.mars.rover.domain.Rover.FaceDirection;
import br.com.rbdutra.mars.rover.domain.Surface;
import br.com.rbdutra.mars.rover.exception.IllegalSurfaceParameterException;

public class ApplicationMain {

	public static void main(String[] args) {

		ApplicationMain main = new ApplicationMain();
		main.runApplication();
	}

	public void runApplication() {

		Scanner scanner = new Scanner(System.in);

		System.out.print(">>> Please, insert the surface width and height: ");
		Surface surface = parseSurfaceInput(scanner.nextLine());

		Rover rover = null;
		List<Command> commands = null;
		List<Rover> allRovers = new ArrayList<>();
		Boolean continueApp = Boolean.TRUE;

		while (continueApp) {

			System.out
					.print(">>> Please, insert the rover positions and direction: ");
			rover = parseRoverInput(scanner.nextLine());

			System.out.print(">>> Please, insert the rover commands: ");
			commands = parseCommandsInput(scanner.nextLine());

			surface.addRover(rover);
			rover.applyCommand(commands);

			allRovers.add(rover);
			
			System.out.println(">>> Please, insert [S] to add another rover or anything else to stop");
			continueApp = parseInsertAnotherRoverInput(scanner.nextLine());
		};

		printRovers(allRovers);

		scanner.close();
	}

	private void printRovers(List<Rover> allRovers) {

		System.out.println("=== ALL ROVERS ===");
		for (Rover rover : allRovers)
			System.out.println(rover);
	}

	private Surface parseSurfaceInput(String surfaceInput)
			throws IllegalArgumentException, IllegalSurfaceParameterException {

		Pattern surfaceInputPattern = Pattern
				.compile("^\\s*(\\d{0,3})\\s(\\d{0,3})\\s*$");

		if (StringUtils.isEmpty(surfaceInput)
				|| !surfaceInputPattern.matcher(surfaceInput).matches())
			throw new IllegalArgumentException(
					"Surface input must follow the pattern: INT INT");

		Matcher matcher = surfaceInputPattern.matcher(surfaceInput);
		matcher.find();

		Integer width = Integer.parseInt(matcher.group(1));
		Integer height = Integer.parseInt(matcher.group(2));

		return new Surface(height, width);
	}

	private Rover parseRoverInput(String roverInput)
			throws IllegalArgumentException {

		Pattern roverInputPattern = Pattern
				.compile("^\\s*(\\d{0,3})\\s(\\d{0,3})\\s(N|n|S|s|W|w|E|e)\\s*$");

		if (StringUtils.isEmpty(roverInput)
				|| !roverInputPattern.matcher(roverInput).matches())
			throw new IllegalArgumentException(
					"Rover input must follow the pattern: INT INT (N|S|W|E)");

		Matcher matcher = roverInputPattern.matcher(roverInput);
		matcher.find();

		Integer x = Integer.parseInt(matcher.group(1));
		Integer y = Integer.parseInt(matcher.group(2));
		FaceDirection faceDirection = FaceDirection.valueOf(matcher.group(3)
				.toUpperCase());

		return new Rover(new Position(x, y), faceDirection);
	}

	private List<Command> parseCommandsInput(String commandInput)
			throws IllegalArgumentException {

		if (StringUtils.isEmpty(commandInput)
				|| !commandInput.matches("^(L|l|R|r|M|m)+$"))
			throw new IllegalArgumentException(
					"Commands input must follow the pattern: L|R|M");

		List<Command> commands = new ArrayList<>();
		for (String commandString : commandInput.split("|"))
			commands.add(Command.valueOf(commandString.toUpperCase()));

		return commands;
	}

	private Boolean parseInsertAnotherRoverInput(String insertAnotherRoverInput) {

		if (!StringUtils.isEmpty(insertAnotherRoverInput)
				&& insertAnotherRoverInput.matches("^\\s*S|s\\s*$"))
			return Boolean.TRUE;

		return Boolean.FALSE;
	}
}