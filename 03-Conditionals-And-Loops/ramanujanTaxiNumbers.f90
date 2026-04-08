!*******************************************************************************
! Program: ramanujanTaxiNumbers
! Description: S. Ramanujan was an Indian mathematician who became famous for 
! his intuition for numbers. When the English mathematician G. H. Hardy came to
! visit him in the hospital one day, Hardy remarked that the number of his taxi
! was 1729, a rather dull number. To which Ramanujan replied, No, Hardy! It is 
! a very interesting number. It is the smallest number expressible as the sum 
! of two cubes in two different ways." 
! 
! The program takes integer command-line argument N0 and N1 and prints the
! different ways in which these integers [N0, N1] can be expressed as the sum 
! of two cubes.
! Also check if a license plate 87539319 would be a rather dull number. 
!*******************************************************************************
    PROGRAM ramanujanTaxiNumbers
    IMPLICIT NONE
    INTEGER :: N0, N1
    INTEGER :: N, p, q, nMax, nSolutions
    CHARACTER(len=32) :: arg1, arg2
    INTEGER :: i, status
    REAL :: progress, percent
    INTEGER :: barWidth, iposition
    CHARACTER(len=50) :: bar
    
    ! Initialize default values
    N0 = 1
    N1 = 2000
    barWidth = 50
    
    ! Display max 32-bit integer value (equivalent to Java's Integer.MAX_VALUE)
    WRITE(*,*) "Max 32 bit integer = ", HUGE(0)
    
    ! Check command line arguments
    IF (COMMAND_ARGUMENT_COUNT() == 2) THEN
        CALL GET_COMMAND_ARGUMENT(1, arg1, status=status)
        IF (status == 0) THEN
            READ(arg1, *, IOSTAT=status) N0
        END IF
        
        CALL GET_COMMAND_ARGUMENT(2, arg2, status=status)
        IF (status == 0) THEN
            READ(arg2, *, IOSTAT=status) N1
        END IF
    END IF
    
    WRITE(*,*) "Processing numbers from ", N0, " to ", N1
    WRITE(*,*) ""
    
    ! Loop on the numbers from N0 to N1
    DO N = N0, N1
        nMax = INT((REAL(N))**(1.0/3.0))
        
        ! Number of solutions
        nSolutions = 0
        
        DO p = 1, nMax
            DO q = nMax, p, -1
                IF (p*p*p + q*q*q == N) THEN
                    nSolutions = nSolutions + 1
                    WRITE(99, '(I0, X, I0, X, I0)') p, q, N
                END IF
            END DO
        END DO
        
        ! Update progress bar
        progress = REAL(N - N0 + 1) / REAL(N1 - N0 + 1)
        percent = progress * 100
        iposition = INT(progress * barWidth)
        
        ! Build the progress bar string
        bar = REPEAT("=", iposition) // REPEAT(" ", barWidth - iposition)
        
        ! Print progress bar (using carriage return to overwrite)
        WRITE(*, '(A, F5.1, A, A, A)', ADVANCE="NO") &
            "Progress: ", percent, "% [", bar, "]"
        
        ! Flush output
        CALL FLUSH(6)
        
        ! Add carriage return to overwrite the line
        IF (N < N1) THEN
            WRITE(*, '(A)', ADVANCE="NO") CHAR(13)
        ELSE
            WRITE(*,*)  ! Final newline after completion
        END IF
    END DO
    
    WRITE(*,*) ""
    WRITE(*,*) "Complete!"
    
    END PROGRAM ramanujanTaxiNumbers
