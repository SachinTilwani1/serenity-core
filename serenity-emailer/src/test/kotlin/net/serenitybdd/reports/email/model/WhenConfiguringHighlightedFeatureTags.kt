package net.serenitybdd.reports.email.model

import net.serenitybdd.reports.email.SerenityEmailReport
import net.thucydides.core.util.MockEnvironmentVariables
import net.thucydides.core.util.SystemEnvironmentVariables
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File

/**
 * A list of highlighted tags appears on the email summary report.
 * By default, feature tags are displayed. You can use the report.tagtypes property to define specific tag-types
 * that should appear in the summary.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WhenConfiguringHighlightedFeatureTags {

    @Test
    fun `Features normally appear on the report summary page`() {
        val environmentVariables = MockEnvironmentVariables()

        // WHEN
        val displayedTagGroups = SerenityEmailReport.tagTypes().configuredIn(environmentVariables)

        // THEN
        assertThat(displayedTagGroups).containsExactly("feature")
    }

    @Test
    fun `The tag groups that appear on the report summary page can be configured using the report-dot-tagtypes property`() {
        val environmentVariables = MockEnvironmentVariables()

        // GIVEN
        environmentVariables.setProperty("report.tagtypes","epic, story")

        // WHEN
        val displayedTagGroups = SerenityEmailReport.tagTypes().configuredIn(environmentVariables)

        // THEN
        assertThat(displayedTagGroups).containsExactly("epic","story")
    }
}