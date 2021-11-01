<script>
import { Pie } from 'vue3-chart-v2'

export default {
  extends: Pie,
  props: ['chartdata', 'options'],
  mounted () {
    this.renderChart(this.chartdata, this.options)
  }
}
</script>

<style>
</style>