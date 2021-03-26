# api-rest-assured-junit5

This project uses

- junit 5
- assertj-core
- json-unit
- install intellij plugin gherkin and cucumber for java  for proper gherkin formatting
- check that cucumber.execution.dry-run= false in  junit-platform.prperties file before running (otherwise the project will only do a dry-run)
- cucumber.plugin proprty in  junit-platform.prperties determines the typr of report
- Integrate with azure pipelines (YAML) and generates cucumber report in the pipeline.



