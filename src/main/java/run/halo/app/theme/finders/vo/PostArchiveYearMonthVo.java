package run.halo.app.theme.finders.vo;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * Post archives by month.
 *
 * @author guqing
 * @since 2.0.0
 */
@Value
@Builder
public class PostArchiveYearMonthVo {

    String month;

    List<ListedPostVo> posts;
}
