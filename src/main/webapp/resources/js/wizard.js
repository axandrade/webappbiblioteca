// Uncomment to enable click functionality

 $(document).ready(function() {
    $('.step').each(function(ix, el) {
        $(this).attr('id', 'step' + (++ix) );
    });
    
    $('.step').on('click', function() {
        var _clicked = $(this);
        var active_found = false;
        
        $('.step').each(function(ix, el) {
            var parent = $(this).parent();
            
            if ( $(this).attr('id') == _clicked.attr('id') ) {
                active_found = true;
                parent.attr('class', 'active');
            } else {
                if ( !active_found ) {
                    parent.attr('class', 'complete');
                } else {
                    parent.attr('class', '');
                }
            }
        });
    });
}); 
